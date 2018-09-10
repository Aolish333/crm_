<?php
//路由
$action = $_GET['action'];
switch($action){
	case 'init_data_list':
	init_data_list();
	break;
	case 'add_row':
	add_row();
	break;
	case 'del_row':
	del_row();
	break;
	case 'edit_row':
	edit_row();
	break;
}

//初始化数据
function init_data_list(){
	$sql = "SELECT create_time,plan_content FROM `crm_plan` ";//下面函数的参数
	$query = query_sql($sql);//自定义函数未定义形参
	while ($row = $query->fetch_assoc()) {
		$data[] = $row;
	}
	echo json_encode($data);exit();
	
}

//新增行记录
function add_row(){
	$sql = 'INSERT INTO `crm_plan` ( `create_time`,`plan_content`  ) VALUES ( ';
	for($i = 0;$i<2;$i++){
		$sql .= '\'' . $_POST['col_' . $i] . '\',';
	}
	$sql = trim($sql,',');
	$sql .= ')';
	$lastInsertId = "SELECT LAST_INSERT_ID() AS LD";
	if($res = query_sql($sql,$lastInsertId)){
		$d = $res->fetch_assoc();
		echo $d['LD'];exit();
	}else{
		echo "db error...";exit();
	}
}

//删除行记录
function del_row(){
	$dataid = $_POST['dataid'];
	$sql = "DELETE FROM `crm_plan` where `id` = " . $dataid;
	if(query_sql($sql)){
		echo "ok";exit();
	}else{
		echo "db error...";exit();
	}
}

//编辑行记录
function edit_row(){
	$sql = "UPDATE 	`crm_plan` SET ";
	$id = $_POST['id'];
	unset($_POST['id']);
	for($i=0;$i<8;$i++){
		$sql .= '`c_'.chr(97 + $i) . '` = \''.$_POST['col_' . $i] . ' \','; 
	}
	$sql = trim($sql,',');
	$sql .= ' WHERE `id` = ' . $id;
	if(query_sql($sql)){
		echo "ok";exit();
	}else{
		echo "db error...";exit();
	}
}

//数据库查询
function query_sql(){
	$mysqli = new mysqli('127.0.0.1','root','1234','crm_');
	$sqls = func_get_args();//获取函数的所有参数
	foreach ($sqls as $key => $value) {
		$query = $mysqli->query($value);
	}
	$mysqli->close();
	return $query;
}


