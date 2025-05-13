let paginaActual = 0;
const tamanoPagina = 10;
let tabla = document.getElementById("tabla2-container").getElementsByTagName("table")[0];
let prevBtn = document.getElementById("prevBtn");
let nextBtn = document.getElementById("nextBtn");
let firstBtn = document.getElementById("firstBtn");
let lastBtn = document.getElementById("lastBtn");

document.getElementById("newEmp").addEventListener("click", function() {
	window.location.assign("http://localhost:8080/PortalEmpleados/new/empleado/buttons");
});

document.addEventListener("DOMContentLoaded", function() {
	reloadPage2("http://localhost:8080/PortalEmpleados/api/empleados");
});

/*
document.addEventListener("DOMContentLoaded", function() {
	reloadPage(0);
});

prevBtn.addEventListener("click", () => reloadPage(-1));
nextBtn.addEventListener("click", () => reloadPage(1));
*/
prevBtn.addEventListener("click", () => reloadPage2(prevBtn.getAttribute("href")));
nextBtn.addEventListener("click", () => reloadPage2(nextBtn.getAttribute("href")));
firstBtn.addEventListener("click", () => reloadPage2(firstBtn.getAttribute("href")));
lastBtn.addEventListener("click", () => reloadPage2(lastBtn.getAttribute("href")));

/* 
 * El siguiente código es para la paginación de la tabla,
 * 	es el antiguo sin la paginacion por Spring HATEOAS. 
 */
function reloadPage(siguiente) {
	paginaActual += siguiente;
	if (paginaActual < 0) paginaActual = 0;

	fetch(`http://localhost:8080/PortalEmpleados/api/tabla2?page=${paginaActual}&size=${tamanoPagina}`)
		.then(response => response.json())
		.then(data => {
			if(data.content.length !== 0) {
				updateTable(data.content)
			}else{
				paginaActual--;
			}
			updateButtons(data.content.length);
		})
		.catch(error => console.error('Error:', error));
}

function updateButtons(numEmployees) {
	prevBtn.disabled = (paginaActual === 0);
	nextBtn.disabled = (numEmployees < tamanoPagina);
}

/*
 * El siguiente código es para la paginación de la tabla,
 * 	es el nuevo con la paginacion por Spring HATEOAS. 
 */
function reloadPage2(url) {
	fetch(url).then(response => response.json())
	.then(data => {
		updateData(data._embedded,data._links);
	}).catch(error => console.error('Error:', error));
}

function updateData(jsonData, links) {
	let employees = jsonData.empleadoes;
	updateTable(employees);
	updateActionsButtons(links);
}

function updateActionsButtons(links) {
	prevBtn.disabled = (links.prev === undefined);
	nextBtn.disabled = (links.next === undefined);
	prevBtn.setAttribute("href", links.prev ? links.prev.href : "#");
	nextBtn.setAttribute("href",  links.next ? links.next.href : "#");
	firstBtn.setAttribute("href", links.first.href);
	lastBtn.setAttribute("href", links.last.href);
}
	
function updateTable(empleados) {
	const cuerpoTabla = tabla.getElementsByTagName("tbody")[0];
	cuerpoTabla.innerHTML = "";

	empleados.forEach(empleado => {
		newRowTable(cuerpoTabla, empleado._links.delete.href, empleado.dni, empleado.nombre, empleado.apellidos);
	});
}

function newRowTable(tableBody, hrefdelete, ...valores) {
	let tableRow = document.createElement("tr");
	valores.forEach(element => {
		let dato = document.createElement("td");
		let text = document.createTextNode(element);
		dato.appendChild(text);
		tableRow.appendChild(dato);
	});
	let datoBtnDelete = document.createElement("td");
	let btnDelete = document.createElement("button");
	let nameBtnDelete = document.createTextNode("Eliminar");
	btnDelete.appendChild(nameBtnDelete);
	btnDelete.setAttribute("data-href_delete", hrefdelete);
	btnDelete.addEventListener("click", deleteEmpleado);
	btnDelete.setAttribute("class", "btn btn-outline-danger");

	datoBtnDelete.appendChild(btnDelete);

	let datoBtnUpdate = document.createElement("td");
	let btnUpdate = document.createElement("button");
	let nameBtnUpdate = document.createTextNode("Modificar");
	btnUpdate.appendChild(nameBtnUpdate);
//	btnUpdate.setAttribute("data-el_id", id);
//	btnUpdate.addEventListener("click", ()=>{
//		window.location.assign("http://localhost:8080/PortalEmpleados/new/empleado/buttons?id="+id);
//	});
	btnUpdate.setAttribute("class", "btn btn-outline-secondary");
	datoBtnUpdate.appendChild(btnUpdate);

	tableRow.appendChild(datoBtnDelete);
	tableRow.appendChild(datoBtnUpdate);
	tableBody.appendChild(tableRow);
}

function deleteEmpleado() {
	const hrefdelete = this.getAttribute("data-href_delete");
	fetch(hrefdelete).then(response => {
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
	fetch(`http://localhost:8080/PortalEmpleados/update/buttons/${id}`).then(response => {
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