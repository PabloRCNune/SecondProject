$(document).ready(function() {
	$('#myTable').DataTable(
		{
			columnDefs: [
				{ className: "dt-center", targets: "_all" },
				{ orderable: false, targets: [3] },
				{ searchable: false, targets: [3] }
			],
			processing: true,
			serverSide: true,
			ajax: {
				url: "http://localhost:8080/PortalEmpleados/api/tabla4",
				type: "GET",
				data: function(d) {
					d.limit = d.length;
					d.offset = d.start;
					d.draw = d.draw;
				},
			},

			language: {
				"url": "https://cdn.datatables.net/plug-ins/2.3.0/i18n/es-ES.json"
			},

			columns: [
				{ data: 'dni' },
				{ data: 'nombre' },
				{ data: 'apellidos' },
				{	
					data: null,
					render: function(data, type, row) {
						return `<button class="btn btn-outline-danger deletebtn" data-el_id="${row.id}"><i class="bi bi-trash3-fill"></i></button>`;
					}
				},
			],

		}
	);
});
