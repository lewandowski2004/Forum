$(function() {
	// Data Table
	$(document).ready(function(){
	    $('#myTable').DataTable( {
	        'ordering': false,
	        lengthMenu: [[3,5,10,25,-1],['3','5','10','25','All']],
			pageLength: 10 ,
			"language": {
			"search": "",
			 searchPlaceholder: "Szukana fraza ..."},
			
			initComplete : function() {
			        var input = $('.dataTables_filter input').unbind(),
			            self = this.api(),
			            $searchButton = $('<button class="btn btn-info" style="margin-left: 15px">')
			                       .text('Szukaj')
			                       .click(function() {
			                          self.search(input.val()).draw();
			                       })
			        $('.dataTables_filter').append($searchButton);
			    }            
			}) 
	  
	});
	// timeout alert
	var $alert = $('.alert');
	
	if($alert.length){
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}
});  

