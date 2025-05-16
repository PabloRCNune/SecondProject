let paginaActual = 0;
const tamanoPagina = 10;
let tabla = document.getElementById("tabla2-container").getElementsByTagName("table")[0];
let deleteButtons = document.getElementsByClassName("deletebtn");

for (const element of deleteButtons) {
	element.addEventListener("click", deleteEmpleado);
}

function deleteEmpleado() {
	const id = this.getAttribute("data-el_id");
	fetch(`http://localhost:8080/PortalEmpleados/delete/buttons/${id}`).then(response => {
		if (response.ok) {
			tabla.deleteRow(this.parentElement.parentElement.rowIndex)
		} else {
			console.error("Error al eliminar el empleado");
		}
	});

}

//mirar luego
/*
function updateEmpleado() {
	const id = this.getAttribute("data-el_id");
	fetch(`data-el_id`).then(response => {
        if (response.ok) {
            reloadPage(0);
        } else {
            console.error("Error al eliminar el empleado");
        }
    });
}
*/
/*
function AssingModButton(){
	let btns = document.getElementsByClassName("updatebtn");

	for (const element of btns) {
		element.addEventListener("click", function() {
			const id = this.getAttribute("data-el_id");
			fetch(`http://localhost:8080/PortalEmpleados/new/empleado/buttons?id=${empleado.id}`).then(response => {
				if (response.ok) {
					UpdateTable();
				}else {
					console.error("Error al eliminar el empleado");
				}
			});
		});
	}
}

*/