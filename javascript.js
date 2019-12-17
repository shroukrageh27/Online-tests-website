/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//sign up page javaScript code

    function validate() {

        if( document.myForm.fName.value === "" ) {
            alert( "Please provide your first name!" );
            document.myForm.fName.focus();
            return false;
        }
        if( document.myForm.sName.value === "" ) {
            alert( "Please provide your surname!" );
            document.myForm.sName.focus() ;
            return false;
        }
        if( document.myForm.email.value === "" ) {
            alert( "Please provide your email or phone number!" );
            document.myForm.email.focus() ;
            return false;
        }
        return( true );
    }
//***************************************************************************************

//login page javaScript code

function validate2() {
        if( document.form.email.value === "" ) {
            alert( "Please provide your email or phone number!" );
            document.form.email.focus() ;
            return false;
        }
        if( document.form.pass.value === "" ) {
            alert( "Please provide your password!" );
            document.form.pass.focus() ;
            return false;
        }
        return( true );
    }
    
//***************************************************************************************

//candidates page javaScript code

function validate3() {
    if( document.fform.type.value === "" ) {
        alert( "Please provide the number of exam!" );
        document.fform.type.focus() ;
        return false;
    }
    return( true );
}