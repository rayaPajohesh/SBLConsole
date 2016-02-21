function refreshCaptcha(){

	document.getElementById('captchaImage').src  =document.getElementById('captchaImage').src + "?" + Math.random();;
}

function disableCancelButton(obj) {	
		document.getElementById(obj).disabled = true;	
}	
	
	function goBack(type){
				
		var action = "";

		if(type == 'limitation')
		{
		action = 'blackListManagement.do?param=limit';
		}
		window.parent.location.replace(action)	
	}
	


	function submitPageByComboBox(e,obj){
	if (e.keyCode == 13) {
		document.forms(0).submit();
	}
	}
	
//added by hashemi 88/06/24


   function checkText1(obj1,obj2,nextObj){
   
        obj = document.getElementById(obj1).value+document.getElementById(obj2).value.substring(0,2);
		conditionalObj=document.getElementById(nextObj);
		  //alert(conditionalObj);
			
      if(obj == '627353' || obj == '589210' || obj == '622016')
      {
     
            conditionalObj.disabled = false;
            conditionalObj.style.backgroundColor = 'White';  
           	conditionalObj.style.border = '1px solid';
           	conditionalObj.style.borderColor = '#0A0756';
            // alert(obj);
        }
   
      else{
     		
            conditionalObj.value="";
            conditionalObj.onkeyup();
            conditionalObj.disabled=true;
            conditionalObj.style.backgroundColor = 'Gainsboro';  
           	conditionalObj.style.border = '1px solid';
           	conditionalObj.style.borderColor = 'silver';
      }
}

         function conditionalFocusNext(e,from, to1,to2,number)
       { 
            var strAllowed = '0123456789';
 			 keyPressed = String.fromCharCode(e.keyCode);
 			  rv=valid(document.getElementById(from),"^ *[0-9]+$");
    			if (!rv) {
      				document.getElementById(from).value = "";
    			}
              if ((strAllowed.indexOf(keyPressed) == -1 && !(e.keyCode>=96 && e.keyCode<=105) && e.keyCode != 8 && e.keyCode != 13)  ){
				document.getElementById(from).value = '';
               } 
            
            else{  
         
            if (document.getElementById(from).value.length == number) {
            
 		if (!document.getElementById(to1).disabled)
 		{
               document.getElementById(to1).focus();
            
          }
          else if (to2!=null)
          {
           document.getElementById(to2).focus();
          } 
            }
        }
        }

//end by Hashemi 88/06/24
  
  
  
  
  function focusNext(e,from, to,number)
       {   
        
   
            var strAllowed = '0123456789';
 			 keyPressed = String.fromCharCode(e.keyCode);
 			 rv=valid(document.getElementById(from),"^ *[0-9]+$");
    			if (!rv) {
      				document.getElementById(from).value = "";
    			}
              if ((strAllowed.indexOf(keyPressed) == -1 && !(e.keyCode>=96 && e.keyCode<=105) && e.keyCode != 8 && e.keyCode != 13)  ){
				document.getElementById(from).value = '';
               } 
            
            else{  
         
            if (document.getElementById(from).value.length == number) {
 
                document.getElementById(to).focus();
            
               }
            }
          
        }


 
        //start Roohi,ashrafi added 87/06/18
         function redirectToMerchant() {
          window.location=document.forms(0).URL.value+"?transactionState=CANCEL";                 
           return;
        } 
        //end Roohi,ashrafi added 87/06/18


/*
	Decode Special characters in Form 
*/
function DecodeForm() {
	var formName = document.forms[0].name;
	eval(document.name);
	
	var len = document.forms[0].length;
	
	for(var i=0 ; i<len ; i++)
	{
		if(document.forms[0].elements[i].type == 'text' || document.forms[0].elements[i].type == 'textarea' || document.forms[0].elements[i].type == 'file')
		{
			document.forms[0].elements[i].value = getDecodeString(document.forms[0].elements[i].value);
		}
	}
}

/*
	Decode String 
*/
function getDecodeString(newStr){	

	newStr = newStr.split("<br>").join("\r");	
	return newStr;
}

/*
	Cancel Confirm messege 
*/
function CancelConfirm(action,URL) {
	if(action=="Cancel"){
		var result = confirm('This will cancel the operation and abandon your changes. The record will not be saved. Are you sure?'); 
		if(result){
			window.open(URL,'_parent');
		}
	}

}	
function CancelConfirm2(action,URL,Id) {
	if(action=="Cancel"){
		var result = confirm('This will cancel the operation and abandon your changes. The record will not be saved. Are you sure?'); 
		if(result){
			window.open(URL+Id,'_parent');
		}
	}
}	

/*
	Open URL in New Window
*/
function PrintPage(pagename) {
	var width = 625;
	var Xpos = (screen.availWidth-625)/2;
	var height = screen.availHeight - (screen.availHeight*0.2)/2;
	var features = 'top=25,ScreenX='+Xpos+',ScreenY=10,left='+Xpos+',directories=no,location=no,menubar=no,resizable=no,status=no,toolbar=no,scrollbars=yes,width='+width+',height='+height;	
	window.open(pagename,'_blank',features);
}

/*
	Open URL in New Window
*/
function OpenPage(pagename) {
	var width = screen.availWidth-40; 
	var height = screen.availHeight-40;
	var features = 'top=20,ScreenX=20,ScreenY=20,left=20,directories=yes,location=yes,menubar=yes,resizable=yes,status=yes,toolbar=yes,scrollbars=yes,width='+width+',height='+height;	
	window.open(pagename,'_blank',features);
}

function changeColor(colorName){
	var isInternetExplorer = navigator.appName.indexOf("Microsoft") != -1;
 	var sty =	document.stylesheets;
 	alert(sty[0]);
 
 	
}


	function valid(fld) // varying number of arguments
	{
	  var i;
	  // scan regular expressions
	  //third argument of valid method is Regular expression
	  for (i=1;i<valid.arguments.length;i++) 
		{
		var rx;
		rx=new RegExp(valid.arguments[i]);
		if (rx.exec(fld.value)!=null ) 
		   return true;  // ok
		}
	  // no matches...
	  //alert(errm);
	  
	  return false;
	}
	
	 /*
	 function to validate the contents of text only Fields
	 param fld = field's Object
	 return true/false
	*/
	function validateName(fld)
	{
	  rv=valid(fld,"^ *[a-zA-Z ]+$");
	  return rv;
	}

	function validateString(fld)
	{
	  rv=valid(fld,"^ *[a-zA-Z ]+$");
    if (!rv) {
      alert('Only insert alphabets in this field');
      fld.value = "";
    }
	  return rv;
	}
	 
	function validateNameAndNumber(fld)
	{
	  if (fld.value != "") {
		rv=valid(fld,"^ *[a-zA-Z0-9 ]+$");
	    if (!rv) {
	      alert('Please avoid special characters in this field');
	      fld.value = "";
	    }
		return rv;
	  }
	  return true;
	}
	
	function validateMobileNumber(fld)
	{

		  if (fld.value != "") {
		  	if(fld.value.length ==10){
//				rv=valid(fld,"^ *[a-zA-Z0-9 ]+$");
				rv=valid(fld,"^[0][5][0|5]{1}[0-9]{7}$");
			    if (!rv) {
			      alert('Please enter correct mobile number for example: 055xxxxxxx');
			      fld.value = "";
			    }
				return rv;
			 }
			 else {
				 alert('Mobile Number should be of 10 digits');
			      fld.value = "";
				 return false;
			 }
			  
		  }
		  return true;
	}
		
	function validateNumber(fld)
	{
	  rv=valid(fld,"^ *[0-9]+$");
    if (!rv) {
      fld.value = "";
    }
    
	}
	/*
	function to validate the contents of Telephone number Fields
	param fld = field's Object
	return true/false
	*/
	  function validatePhone(fld)
	  {
		rv=valid(fld,"^ *[0-9 ()-]+$");
		  
		return rv;
	  } 
	  /*
	 function to validate the contents of email Fields
	 param fld = field's Object
	 return true/false
	*/
	  function validateEmail(fld)
	  {
	  rv=valid(fld,"^ *[-a-zA-Z0-9_]+\.?[-a-zA-Z0-9_]+@[-a-zA-Z0-9_]+\\.[a-zA-Z][a-zA-Z][a-zA-Z]?\.?[a-zA-Z]+$");
    if (!rv) {
      alert('Please enter a valid Email Address in this field');
      fld.value = "";
    } 				  
	  return rv;
	  }

	  function validateDate(fld)
	  {
	  rv=valid(fld, "^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$");
    if (!rv) {
      alert('Please enter a valid Date in this field. Required Format is DD/MM/YYYY');
      fld.value = "";
    }
	  return rv;
	  }
	  
	  function validateFloatNumber(fld)
	  {
	  if (fld.value.length > 15)
	  {
	  	alert('Amount length cannot be greater then 15 characters.');
        fld.value = "0.00";
        return false;
	  }
	  rv=valid(fld,"^[0-9]{1,7}\.?[0-9]{1,2}$");
      if (!rv) {
         alert('Amount must be numeric and in correct format, i.e. XXXX or XXXX.XX');
         fld.value = "0.00";
       }    
	  return rv;
	  }
	  
	  var isError = false;//variable used as a flag to determine there was any error or not
    
    
    function validateAlertForm() {
      var formName = document.forms['alertRegistrationForm'].name;
      eval(document.name);
      
      var len = document.forms['alertRegistrationForm'].length;

      for(var i=0 ; i<len ; i++)
      {

        if(document.forms['alertRegistrationForm'].elements[i].type == 'text')
        { 

          if (document.forms['alertRegistrationForm'].elements[i].value == '') {
            alert('Please fill all input fields');
            return false;
          }
        }
      }
    }
    
   //added by hashemi 88/04/09:set format for currencymode 
	  

	function mask(str,textbox){

	if(str.indexOf('.')==-1){     

	textbox.value = mask1(str,textbox) ;
	}else if(str.indexOf('.')>-1){

	var str2 =  str.substring(0,	str.indexOf('.'));
	var afpoint = str.substr(str.indexOf('.')) ;
	textbox.value = mask1(str2,textbox) + afpoint ;

	}

	}

	function mask1(str,delim){           

	str=str.replace(/[^0-9]+/g,'');

	str= reverse(str);
	var str3="";

	var i=0;

		for (var k = 0; k < str.length; k++){

		 str3=str3 + str.charAt(k);

		 if(i==2){
		 i=0;

			if(k!=(str.length-1)){

				str3=str3  + ","
			}
		 }else{

		 i++;

		 }
		}

		if(str3.length>0){
		str=reverse(str3);

		}else{

		str = "";
		}

        return  str;

		}

	function reverse(theString){  //Reverse a String

	  var newString = "";
      var counter = theString.length; 

	for (counter  ;counter > 0 ;counter -- ) {
         newString += theString.substring(counter-1, counter);
      } 

	return newString;
	}
	
		
	function validateNumberwithcomma(fld)
	{
	  rv=valid(fld,"^ *[0-9,]+$");
    if (!rv) {
      fld.value = "";
    }
    
	}
    //end hashemi  
	  
	function secconv () {
    	var myS1 =  document.loginForm.pin.value; 
    	alert(myS1);
     	var myS2 =  document.loginForm.cvv2.value; 
     	alert(myS2);
     	var reverseS1 = "";
  	    var reverseS2 = "";
		var i1 = myS1.length;
		var i2 = myS2.length;
			
		i1=i1-1;
		i2=i2-1;
		for (var x1 = i1; x1 >=0; x1--)
		{
		
		var temp1 = myS1.charAt(x1);
		 reverseS1 =  reverseS1 + temp1;
		}
		
		for(var x2 = i2; x2 >=0; x2--)
		{
		
		var temp2 = myS2.charAt(x2);
		 reverseS2 =  reverseS2 + temp2;
		} 
    	document.loginForm.pin.value = reverseS1;
 	    document.loginForm.cvv2.value = reverseS2;
    }
    
        