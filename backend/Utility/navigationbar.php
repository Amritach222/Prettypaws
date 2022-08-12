<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Brand and toggle get grouped for better mobile display -->
    <nav class="navbar sticky-top navbar-light bg-light justify-content-between">
  <a class="navbar-brand"  href="https://localhost/dogFinderApp/home.php">Dashboard</a>
  <form class="form-inline">
    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    <div class=" dropdown" >
  <button style="background:#0000;"class="btn  dropdown-toggle"  id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  <img src="https://img.icons8.com/android/20/000000/user.png">
  </button>
  <div class="dropdown-menu dropdown-menu-left" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="https://localhost/dogFinderApp/Utility/setting.php"><img src="https://img.icons8.com/material-sharp/20/000000/settings.png"><strong>Setting</strong></a>
    <a class="dropdown-item" href="https://localhost/dogFinderApp/Utility/logout.php"><img src="https://img.icons8.com/metro/20/000000/exit.png"><strong>Logout</strong></a>
    
  </div>
</div>
  </form>
</nav>
<style>
  i.fas{
    margin:10px; cursor:pointer;
    font-size:25px;

  }
  i.fas:hover{
    color:blue;
    font-size:30px;
  }
</style>
<script>
function anotherpage(){
  window.location.replace
}
</script>
</body>
</html>