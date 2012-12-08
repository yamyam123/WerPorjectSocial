  function onlyNumber(event) {
	    var key = window.event ? event.keyCode : event.which;    

	    if ((event.shiftKey == false) && ((key  > 47 && key  < 58) || (key  > 95 && key  < 106)
	    || key  == 35 || key  == 36 || key  == 37 || key  == 39  // 방향키 좌우,home,end  
	    || key  == 8  || key  == 46 ) // del, back space
	    ) {
	        return true;
	    }else {
	        return false;
	    }    
	};
	function select(){
		var pro = parseInt($(".select").val());
		var output='문제<input type="text" name="title"><input type="hidden" name="pnumber" value="'+pro+'"><br>';
		var problem="";
		if(!isNaN(pro)){
			for(var i=0; i<pro; i++)
			{
				problem ='지문 '+(i+1)+'번'+'<input type="text" name="'+(i+1)+'">'+
							'<input type="radio" name="answer" value="'+(i+1)+'"checked="checked">정답 ';
				output += problem;
			}
			output +=  '<br><input type="submit" value="문제 제출">';
				$(".space").html(output);
		}
	}
