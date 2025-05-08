
document.getElementById("newEmp").addEventListener("click", function() {
    window.location.assign("http://localhost:8080/PortalEmpleados/new/empleado/buttons");ç
});

AssingDeleteButton();

function UpdateTable(){
    let tabla = document.getElementById("tabla2-container");
    fetch('http://localhost:8080/PortalEmpleados/tabla2').then(response => response.text()).then(data => {
        tabla.innerHTML = data;
        AssingDeleteButton();
}).catch(error => console.error("Error al cargar la tabla:", error));
}

function AssingDeleteButton(){
    let btnsDelete = document.getElementsByClassName("deletebtn");

    for (const element of btnsDelete) {
    element.addEventListener("click", function() {
        const id = this.getAttribute("data-el_id");
        fetch(`http://localhost:8080/PortalEmpleados/delete/buttons/${id}`).then(response => {
			if (response.ok) {
                UpdateTable();
			}else {
                console.error("Error al eliminar el empleado");
            }
		});
    });
}
}