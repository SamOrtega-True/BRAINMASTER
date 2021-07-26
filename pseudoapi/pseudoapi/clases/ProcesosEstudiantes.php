<?php
  class ProcesosEstudiantes{

    /*function ValidarEstudiante($u){
      $con = new Conexion();
      
      $userExist = mysqli_query($con->Conectar(),"SELECT * FROM usuario WHERE email ='{$u}'");
      if($userExist){
        $query = "CALL Prueba_borrar('{$u}')";
        $login = mysqli_query($con->Conectar(),$query);
        $countRows = mysqli_num_rows($login);
        if($countRows > 0){
          return mysqli_fetch_object($login);
        }
      }
      return null;
    }*/
    
    function ValidarEstudiante($u,$p){
      $con = new Conexion();
      
      $userExist = mysqli_query($con->Conectar(),"SELECT * FROM usuarios WHERE usuario ='{$u}'");
      if($userExist){
        $query = "CALL VerificarUsuarioEstudiante('{$u}','{$p}')";
        $login = mysqli_query($con->Conectar(),$query);
        $countRows = mysqli_num_rows($login);
        if($countRows > 0){
          return mysqli_fetch_object($login);
        }
      }
      return null;
    }

    function ObtenerFacultades(){
      $con = new Conexion();

      $query = "CALL ObtenerFacultades()";
      $allFacultad = mysqli_query($con->Conectar(),$query);
      $countRows = mysqli_num_rows($allFacultad);
      if($countRows > 0){
        $datos = array();
        while($row = mysqli_fetch_assoc($allFacultad)){
            $datos[] = $row;
        }
        return $datos;
      }
      return [];
    }

    function RegistrarEstudianteUsuario($n,$c,$e,$f,$em,$p){
      $con = new Conexion();

      $query ="CALL InsertarEstudianteUsuairo('{$n}','{$c}','{$e}','{$f}','{$em}','{$p}')";

      return mysqli_query($con->Conectar(),$query);

    }

    function MostrarPreguntaPartida($codigo){
      $con = new Conexion();

      $query = "CALL Preguntas_BrainMaster('{$codigo}')";
      $allTabla = mysqli_query($con->Conectar(),$query);
      $countRows = mysqli_num_rows($allTabla);
      if($countRows > 0){
        $datos = array();
        while($row = mysqli_fetch_assoc($allTabla)){
            $datos[] = $row;
        }
        return $datos;
      }
      return [];
    }



    function MostrarRespuestasPartida(){
      $con = new Conexion();

      $query = "CALL Respuestas_BrainMaster()";
      $allTabla = mysqli_query($con->Conectar(),$query);
      $countRows = mysqli_num_rows($allTabla);
      if($countRows > 0){
        $datos = array();
        while($row = mysqli_fetch_assoc($allTabla)){
            $datos[] = $row;
        }
        return $datos;
      }
      return [];
    }


  }
?>