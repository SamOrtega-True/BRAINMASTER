<?php
  require "clases/Conexion.php";
  require "clases/ProcesosEstudiantes.php";
  require "clases/ProcesosJuegos.php";

  $ep = $_GET['ep'];

  if($ep == 'login'){
    $u = $_GET['u'];
    $p = $_GET['p'];
    Login($u,$p);
    //Login($u);
  }else if ($ep == 'juegos'){
    AllJuegos();
  }else if ($ep == 'facultades'){
    AllFacultades();
  }else if ($ep == 'estudiantesSave'){
    $data = json_decode(file_get_contents('php://input'), true);
    $n = $data['nombre_completo'];
    $c = $data['cedula'];
    $e = $data['edad'];
    $f = $data['facultad'];
    $em = $data['email'];
    $p = $data['password'];
    RegistrarEstudiantes($n,$c,$e,$f,$em,$p);
  }else if ($ep == 'preguntas'){
    $jid = $_GET['j'];
    $nid = $_GET['nivelId'];
    $nid = 1;
    PreguntasRespuestas($jid,$nid);
  }else if ($ep == 'partidaSave'){
    $data = json_decode(file_get_contents('php://input'), true);
    RegistrarPartida($data);
  }else if ($ep == 'posiciones'){
    AllPositions();
  }else if ($ep == 'preguntasBrain'){
    $codigo = $_GET['iP'];
    MostrarPreguntas($codigo);
  }else if($ep == 'respuestasBrain'){
    MostrarRespuestas();
  }

  /*function Login($u){
    $estudianteDb = new ProcesosEstudiantes();
    $user = $estudianteDb->ValidarEstudiante($u);
    if($user){
      print json_encode($user);
    }else{
      print json_encode(null);
    }
  }*/

  function MostrarPreguntas($codigo){
    $estudiantesDB = new ProcesosEstudiantes();
    $user = $estudiantesDB->MostrarPreguntaPartida($codigo);
    print json_encode($user);
  }

  function MostrarRespuestas(){
    $estudiantesDB = new ProcesosEstudiantes();
    $user = $estudiantesDB->MostrarRespuestasPartida();
    print json_encode($user);
  }

  function Login($u,$p){
    $estudianteDb = new ProcesosEstudiantes();
    $user = $estudianteDb->ValidarEstudiante($u,$p);
    if($user){
      print json_encode($user);
    }else{
      print json_encode(null);
    }
  }

  function AllJuegos(){
    $juegosDb = new ProcesosJuegos();
    $juegos = $juegosDb->ObtenerTodosLosJuegos();
    print json_encode($juegos);
  }

  function AllFacultades(){
    $estudiantesDb = new ProcesosEstudiantes();
    $facultades = $estudiantesDb->ObtenerFacultades();
    print json_encode($facultades);
  }

  function RegistrarEstudiantes($n,$c,$e,$f,$em,$p){
    $estudiantesDb = new ProcesosEstudiantes();
    $registrado = $estudiantesDb->RegistrarEstudianteUsuario($n,$c,$e,$f,$em,$p);

    if($registrado){
      print json_encode(1);
    }else{
      print json_encode(0);
    }

  }

  function PreguntasRespuestas($jid,$nid){
    $juegosDb = new ProcesosJuegos();
    $preguntas = $juegosDb->ObtenerPreguntas($jid,$nid);
    print json_encode($preguntas);
  }

  function RegistrarPartida($data){
    $juegosDb = new ProcesosJuegos();

    $jg = $data['juego'];
    $ju = $data['jugador'];
    $p = $data['partida'];
    $f = $data['fecha'];
    $h = $data['hora'];
    $pt = $data['puntaje'];
    $d = json_encode($data['detalle']);
    $n = $data['nivel'];

    $registrado = $juegosDb->InsertarPartida($jg,$ju,$p,$n,$f,$h,$pt,$d);

    if($registrado){
      print json_encode(1);
    }else{
      print json_encode(0);
    }
  }

  function AllPositions(){
    $juegosDb = new ProcesosJuegos();
    $pos = $juegosDb->ObtenerPosciiones();
    print json_encode($pos);
  }

?>