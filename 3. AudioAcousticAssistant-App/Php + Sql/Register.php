<?php
	//this imports the password library that does the encryption. 
    require("password.php");
	
	//connecting to the database
    $connect = mysqli_connect("localhost", "id1289576_admin", "audio", "id1289576_lake");
    
   //taking the values from the java application
    $email = $_POST["email"];
    $password = $_POST["password"];
	
	//this code is registering a user, Eg putting the email and passwordHash (eg:encypted password) into the database. 
     function registerUser() {
        global $connect, $email, $password;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO user (email, password) VALUES (?, ?)");
        mysqli_stmt_bind_param($statement, "ss", $email, $passwordHash);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
	//this checks if the "username" is avaliable, We late abanonded the use of usernames, so this checks if the email has already been added to the database. 
    function usernameAvailable() {
        global $connect, $email;
        $statement = mysqli_prepare($connect, "SELECT * FROM user WHERE email = ?"); 
        mysqli_stmt_bind_param($statement, "s", $email);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
	//this tells the user if the email has been used, The outcome of the "usernameAvailable" function. 
    $response = array();
    $response["success"] = false;  
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
	//connects to the app. 
    echo json_encode($response);
?>