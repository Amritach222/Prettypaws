<?php
include('../connection.php');
include('bootstrap.html');
if(isset($_GET['id'])){
    $id=$_GET['id'];
    $select="SELECT dog_table.*, user_table.* FROM dog_table, user_table WHERE dog_table.user_id=user_table.id";
	$result['dog']=array();
	$run_select=mysqli_query($con,$select);
    if($run_select){
        while ($row=mysqli_fetch_array($run_select)) {
            $id=$row[0];
            $dogname=$row[1];
            $doglocation=$row[2];
            $imageStore=$row[3];
            $dogage=$row[4];
            $doggender=$row[5];
            $dogmessage=$row[6];
            $dogbreed=$row[7];
            $userid=$row[8];
            $userEmail=$row[11];

    }
    $query_approve="INSERT INTO approved_dogs(dog_name,dog_address,dog_picture,dog_age,dog_gender,description,dog_breed,user_id) VALUES('$dogname','$doglocation','$imageStore',
    '$dogage','$doggender','$dogmessage','$dogbreed','$userid')";
    $run_query_approve=mysqli_query($con, $query_approve);
    if($run_query_approve){
        $query_delete="DELETE FROM dog_table WHERE id='$id'";
        $run=mysqli_query($con,$query_delete);
        if($run){
            require_once('../PHPMailer/class.smtp.php');

                require_once('../PHPMailer/class.phpmailer.php');

                $mail= new PHPmailer();
                $mail->isSMTP();
                $mail->Host = 'smtp.gmail.com';
                $mail->SMTPAuth= true;
                //$mail->isHTML(true);
                $mail->Username='motherland17102@gmail.com';
                $mail->Password='9806138065';
                $mail->Port ='465';
                $mail->SMTPSecure ='ssl';
                //Email Setting
                $mail->isHTML(true);
                //$mail->setForm('amritach@gmail.com','Amrit Acharya');
                //$mail->Subject = 'Hello world';
                //$mail->Body ='Test Email';
                $mail->AddAddress($userEmail);
                //$mail->addReplyTo('amritac@gmail.com','Amrit Acharya');
                $mail->Subject = 'Approval';
                $mail->Body ="Dog you have added is successfully aprroved.";
                if ($mail->send())
                {
                    echo "<br><div class='alert alert-success text-center'>
                    <strong>email Successfully sent</strong> 
                </div> ";
                echo "<br><div class='alert alert-danger text-center'>
                <strong>Incorrect Email. </strong> 
            </div>";
                    
                }
                else{
                    echo "<br><div class='alert alert-danger text-center'>
                    <strong>Connection Error..</strong> 
                </div> ";
                }
                echo '<script type="text/javascript">',
                    'location.href="../home.php"',
                    '</script>';
                        }
                    else{
                    }
                    
                        }
                        else{
                            echo "Failed to approve";
                        }
                        
                    }
                    else{
                        echo "Conection Problem";
                    }
	
    }


?>