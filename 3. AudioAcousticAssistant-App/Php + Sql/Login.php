<?php

//@reference YouTube - TonikamiTV/Login Register 6 part series - https://www.youtube.com/watch?v=QxffHgiJ64M
	//this imports the password library that does the encryption.
    require("password.php");
	
	//connecting to the database
    $con = mysqli_connect("localhost", "id1289576_admin", "audio", "id1289576_lake");
    
	//taking the values from the java application
    $email = $_POST["email"];
    $password = $_POST["password"];
    
	//Finding the values in the database. 
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ?");
    mysqli_stmt_bind_param($statement, "s", $email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colUserID, $colEmail, $colPassword);
    
	//if it is succesful 
    $response = array();
    $response["success"] = false;  
    
	//checking if the password matches. 
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colPassword)) {
            $response["success"] = true;  
            $response["email"] = $colEmail;
           
        }
    }
	//connects to the app. 
    echo json_encode($response);
?>
