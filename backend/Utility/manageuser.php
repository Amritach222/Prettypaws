<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <?php
    include 'bootstrap.html'; 
    ?>
</head>
<body>
    <?php
    include 'navigationbar.php';
    ?>
    
    <div class="container">
    <table class="table">
  <caption >List of users</caption>
  <thead>
    <tr>
      <th scope="col">S.N.</th>
      <th scope="col">Name of User</th>
      <th scope="col">Email</th>
      <th scope="col">Contact Number</th>
      <th scope="col">Address</th>
      <th scope="col">Image</th>

    </tr>
  </thead>
      <?php
      $count=0;
	require_once('../connection.php');
	 $query="SELECT * FROM user_table ORDER BY name";
	 $result=array();
	 $result['data']=array();
	 $query_run=mysqli_query($con,$query);
	 while($row=mysqli_fetch_array($query_run)){
         $count++;
	 	$id=$row['0'];
	 	$name=$row['1'];
	 	$email=$row['2'];
	 	$contact_number=$row['3'];
	 	$address=$row['4'];
	 	$image=$row['6'];
         echo "<tbody>
         <tr><th scope='row'>$count</th>
         <td>$name</td>
         <td>$email</td>
         <td>$contact_number</td>
         <td>$address</td>
         <td><button  data-toggle='modal' data-target='#exampleModalCenter'> <img onClick='showImage($image);' style='height:50px; width:auto; cursor:pointer;'  src='https://localhost/dogFinderApp/userImage/$image'/></button></td>
         </tr>
  </tbody>";
     }
         
	 mysqli_close($con);

// }
// else{
// 	echo "send data failed!";
// }
?>
</table>

<?php
    include 'footer.php';
    ?>
</body>
<script type='text/javascript'>
    function showImage(image){
      document.write("<?php 
      echo '<!-- Modal -->
      <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <img src="https://localhost/dogFinderApp/userImage/".';
         ?>");
         document.write(image);
         document.write("<?php 
                echo '/>
                      </div>
                      <div class="modal-footer">
                      </div>
                    </div>
                  </div>
                </div> ';
         ?>");
    }
</script>
</html>