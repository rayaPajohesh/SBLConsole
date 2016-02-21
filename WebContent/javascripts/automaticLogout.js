    var activityPerformed = 'true';
 	
 	function logOutWhenIEClose(){
 		if(window.event.clientY < 0 && window.event.clientY < -80){
			window.parent.location.replace('logout.do');
			}
    }
    
	function logOut(){
 		window.parent.location.replace('logout.do');		
    }    
    
    function logoutTimer()
    {
        if ( activityPerformed == 'false' ) 
        {
                window.parent.location.replace('logout.do');
        }
        else
        {
            activityPerformed = 'false';
        }
        
    }
    
    function resetActivity()
    {
        activityPerformed = 'true';   
    }
    
    document.onmousemove = resetActivity;
    document.onkeydown = resetActivity;
    document.onkeypress = resetActivity;
    document.onscroll = resetActivity;
    
    var id = window.setInterval("logoutTimer()",600000);