/**
 * 
 */
$(document).ready(function() 
	    { 
	        $("#customerstable").tablesorter(); 
	        $(".trashbut").click(function(){
	     	   checkboxes = document.getElementsByName("checkbox");
	     	   
	     	   var cont = 0;
	     	   for (var i = 0; i < checkboxes.length; i++) {
	     	   	var checkbox = checkboxes[i];
	     	   	if(checkbox.checked) {
	     	   		cont++;
	     	   	}
	     	   }
	     	   $("#modalCustomersNumber").html(cont);
	         $("#myModal").modal('show');
	     });
	    }); 


//Show the trash & modify buttons in each case
function showblocked() {
	checkboxes = document.getElementsByName("checkbox");
	trash = document.getElementById("deleteCustomer");
	modify = document.getElementById("modifyCustomer");
	var cont = 0;
	for (var i = 0; i < checkboxes.length; i++) {
		var checkbox = checkboxes[i];
		if(checkbox.checked) {
			cont++;
		}
	}

	if(cont != 0)
		trash.style.display='';
	if(cont == 1) 
		modify.style.display='';
	if(cont == 0) {
		modify.style.display='none';
		trash.style.display='none';
	}
	if(cont > 1)
		modify.style.display='none';
}

//Show how much selected checkboxes there is and keep the asociated customers for delete/modify them

function check(v)
{
	checkbox= document.getElementsByName("checkbox");
	var selected = new Array()
	for(var i = 0; i<checkboxes.length; i++){
		var checkbox = checkboxes[i];
		if(checkbox.checked){
			selected.push(checkbox.value);
		}
	}
    if (v==1){
    	document.form.customers.value=selected;
    	document.form.submit();
    }
    else if (v==2){
    	document.formM.customer.value=selected;
    	document.formM.submit();
    }
} 



//Select/Deselect all checkboxes
function AllCheck() {
	checkboxes = document.getElementsByName("checkbox");
	trash = document.getElementById("deleteCustomer");
	modify = document.getElementById("modifyCustomer");
	var checkbox = document.getElementById("SelectAll");
	if(checkbox.checked){
		for(var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = true;
			trash.style.display='';
			modify.style.display='none';
		}
	}
	else {
		for(var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = false;
			modify.style.display='none';
			trash.style.display='none';
		}	
	}
}