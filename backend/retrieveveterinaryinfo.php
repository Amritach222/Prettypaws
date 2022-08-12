<?php
include('connection.php');
	 $query="SELECT * FROM veterinary_table";
	 $result=array();
	 $result['data']=array();
	 $query_run=mysqli_query($con,$query);
	 while($row=mysqli_fetch_array($query_run)){
	 	$index['id']=$row['0'];
	 	$index['name']=$row['1'];
	 	$index['address']=$row['2'];
	 	$index['contact']=$row['3'];
	 	array_push($result['data'],$index);

	 }
	 $result['success']="1";
	 echo json_encode($result);
	 mysqli_close($con);

?>