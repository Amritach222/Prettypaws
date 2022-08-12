<?php
	$oldemail=$_POST['oldemail'];
	$oldpass=md5($_POST['oldpass']);
	require_once('connection.php');
	if($_POST['name']!="1"){
		$name=$_POST['name'];
	$query="UPDATE user_table SET name='$name' WHERE email='$oldemail' and password='$oldpass'";
		if (mysqli_query($con,$query)){
			echo "name updated";
			# code...
		}
		else{
			echo "update name failed!";
		}
	}
	if($_POST['email']!="1"){
		$email=$_POST['email'];
	$query="UPDATE user_table SET email='$email' WHERE email='$oldemail' and password='$oldpass'";
	if (mysqli_query($con,$query)) {
			echo "email updated";
			# code...
		}
		else{
			echo "update email failed!";
		}
	}
	if($_POST['contact']!="1"){
		$contact=$_POST['contact'];
	$query="UPDATE user_table SET contact_number='$contact' WHERE email='$oldemail' and password='$oldpass'";
	if (mysqli_query($con,$query)) {
			echo "contact updated";
			# code...
		}
		else{
			echo "update contact failed!";
		}
	}
	if($_POST['password']!="1"){
		$password=md5($_POST['password']);
	$query="UPDATE user_table SET password='$password' WHERE email='$oldemail' and password='$oldpass'";
	if (mysqli_query($con,$query)) {
			echo "password updated";
			# code...
		}
		else{
			echo "update password failed!";
		}
	}
	if($_POST['oldpass']!="1" && $_POST['oldemail']!="1"){
	$query="UPDATE user_table SET password='$oldpass' WHERE email='$oldemail'";
	if (mysqli_query($con,$query)) {
			echo "password updated";
			# code...
		}
		else{
			echo "update password failed!";
		}
	}

?>