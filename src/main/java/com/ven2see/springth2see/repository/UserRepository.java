package com.ven2see.springth2see.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ven2see.springth2see.model.User;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String SQL = "select * from user_blog";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User userById(int id) {

        String sql = "SELECT * FROM user_blog where id=?";
        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class),
                new Object[] { id });

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User getAdmin(int tip) {
        String sql = "select * from user_blog inner join users_roles on user_blog.id = users_roles.user_id where users_roles.role_id =?";
        User users = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class),
                new Object[] { tip });

        if (users == null) {
            throw new RuntimeException("No se encontraron administradores con el valor de tip: " + tip);
        } else {
            // Devuelve el primer administrador encontrado (asumiendo que debería haber solo
            // uno)
            return users;
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int save(User user) {
        String SQL = "INSERT INTO user_blog(name, lastname, username, email, password, image, status) VALUES(?,?,?,?,?,?,?)";

        // Aplicar la codificación bcrypt a la contraseña
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        return jdbcTemplate.update(SQL, new Object[] { user.getName(), user.getLastname(), user.getUsername(),
                user.getEmail(), encodedPassword, user.getImage(), user.getStatus() });
    }

    @Override
    public int update(User user) {
        String SQL = "UPDATE user_blog SET name=?, lastname=?, username=?, email=?, password=?, image=?, status=? where id=?";

        return jdbcTemplate.update(SQL, new Object[] { user.getName(), user.getLastname(), user.getUsername(),
                user.getEmail(), user.getImage(), user.getStatus() });

    }

    @Override
    public int deleteById(int id) {
        String SQL = "Delete user_blog where id=?";
        return jdbcTemplate.update(SQL, new Object[] { id });
    }

    @Override
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM user_blog WHERE username = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

}
