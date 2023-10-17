const listPost = async () =>{
    const response = await fetch("http://127.0.0.1:9000/api/v1/card/list");
    const posts = await response.json();
    let card=``;
    posts.forEach((post,index)=>{
        card+=generateCardHtml(post);
    });
    
    document.getElementById("cardsBody").innerHTML = card;

    const cardLinks = document.querySelectorAll(".card-1");
    cardLinks.forEach(link=>{
        link.addEventListener("click", ()=>{ 
            const title = link.dataset.title;
            const precio = link.dataset.price;
            const imagen = link.dataset.image;
            
            
        const modal = $('#exampleModal');
        modal.find('#productTitle').text(title);
        modal.find('#productPrice').text(precio);

            const productImage = modal.find('#productImage');
             productImage.attr('src', imagen);

        modal.modal('show');
        });
    });
};

const listInfo = async () =>{
  const response = await fetch("http://127.0.0.1:9000/api/v1/info/list");
  const infoRep = await response.json();
  let infoCard = ``;

  infoRep.forEach((inf,index)=>{
    infoCard = sectionInfo(inf);
  });


  document.getElementById("section-1").innerHTML = infoCard;
};

const generateCardHtml = (post) =>{
    return `<div class="col-lg-4 col-sm-6 mb-4" >
    <div class="card card-1" data-toggle="modal" data-target="#exampleModal" data-title="${post.nombre_post}"
    data-price="${post.precio}" data-image="${post.imagen}">
       <div class="bg-image hover-zoom ripple img-cs" data-mdb-ripple-color="light">
         <img src="${post.imagen}"
           class="w-100" />
       </div>
       <div class="card-body">
         <a href="#" class="text-reset">
           <h5 class="card-title mb-3">${post.nombre_post}</h5>
         </a>
         <h6 class="mb-3">
           <s>${post.precio}</s><strong class="ms-2 text-danger">${post.precio}</strong>
         </h6>
       </div>
     </div>
     </div>`;
};

const sectionInfo = (infoI) =>{
  return `<a id="dcp"></a>
  <article class="card shadow-none border-none" style="width: 35em;">
    <h4>¡Hey!</h4>
    <h2>${infoI.nombre + infoI.apellido}</h2>
    <h3>${infoI.profesion}</h3>
    <p>${infoI.descrip}</p>
  </article>
  <div class="card col-lg-9 col-xs-9 shadow-none divImg d-none d-md-flex" > 
    
    
  </div>`;
};




window.addEventListener("load", function(){
    listPost();
    listInfo();
});

/* <ul
  class="dropdown-menu dropdown-menu-end"
  aria-labelledby="navbarDropdownMenuAvatar"
>
  <li>
    <a class="dropdown-item" href="#">My profile</a>
  </li>
  <li>
    <a class="dropdown-item" href="#">Settings</a>
  </li>
  <li>
    <a class="dropdown-item" href="#">Logout</a>
  </li>
</ul> */