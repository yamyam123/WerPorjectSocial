
   function checkHeart(id){
	      //하트가 왔을대 확인창이 뜨게함
	     if(confirm("하트가 도착했습니다. 확인하시겠습니까?")){
	    	 location.href="user?op=rshow&id="+id;
	     }
	      
	   }
   
   $(document).ready(function(){
	    		if("${log}" == 'logout'){
	    			if($("#heart-number").text()!=0 ){
	    				checkHeart("${user.id}");
	    			}
	    		}
	   });
