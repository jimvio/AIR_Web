#员工表
CREATE TABLE `carsharing`.`employee` (
  `id` INT NOT NULL COMMENT '序号',
  `employee_number` INT NOT NULL COMMENT '工号',
  `name` VARCHAR(20) NOT NULL COMMENT '姓名',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机',
  `register_time` DATETIME NULL COMMENT '注册时间',
  PRIMARY KEY (`id`));


#司机表
CREATE TABLE `carsharing`.`driver` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL COMMENT '员工号',
  `username` VARCHAR(45) NULL COMMENT '用户名',
  `password` VARCHAR(45) NULL COMMENT '密码',
  `name` VARCHAR(45) NULL COMMENT '姓名',
  `phone` VARCHAR(45) NULL COMMENT '手机',
  `id_number` VARCHAR(45) NULL COMMENT '身份证号',
  `status` VARCHAR(45) NULL COMMENT '状态',
  `car_number` VARCHAR(45) NULL COMMENT '车牌号',
  `car_style` VARCHAR(45) NULL COMMENT '车辆类型',
  `car_brand` VARCHAR(45) NULL COMMENT '车辆品牌',
  `car_color` VARCHAR(45) NULL COMMENT '车辆颜色',
  `vin` VARCHAR(45) NULL COMMENT '车辆识别码',
  `engine_no` VARCHAR(45) NULL COMMENT '发动机编号',
  `manufacture_date` DATETIME NULL COMMENT '出厂日期',
  `issue_date` DATETIME NULL COMMENT '初次领证日期',
  `type` VARCHAR(45) NULL COMMENT '准驾车型',
  `idcar_front` VARCHAR(100) NULL COMMENT '身份证正面照',
  `idcar_back` VARCHAR(100) NULL COMMENT '身份证背面照',
  `driving_license` VARCHAR(100) NULL COMMENT '驾驶证照片',
  `vehicle_register` VARCHAR(100) NULL COMMENT '机动车登记证照片',
  PRIMARY KEY (`id`));


#订单表
CREATE TABLE `carsharing`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `driver_name` VARCHAR(45) NULL COMMENT '司机姓名',
  `car_number` VARCHAR(45) NULL COMMENT '车牌',
  `finish_time` DATETIME NULL COMMENT '订单完成时间',
  `passenger_number` INT NULL COMMENT '乘客人数',
  PRIMARY KEY (`order_id`))
COMMENT = '订单表';


#订单详情表
CREATE TABLE `carsharing`.`order_detail` (
  `order_id` INT NOT NULL,
  `name` VARCHAR(45) NULL COMMENT '乘客名',
  `start_address` VARCHAR(100) NULL COMMENT '上车点',
  `end_address` VARCHAR(100) NULL COMMENT '下车点',
  `price` VARCHAR(45) NULL COMMENT '价格')
COMMENT = '订单详情';


#管理人员表
CREATE TABLE `carsharing`.`manager` (
  `manager_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL COMMENT '用户名',
  `password` VARCHAR(45) NULL COMMENT '密码',
  `employee_number` INT NULL COMMENT '工号',
  PRIMARY KEY (`manager_id`))
COMMENT = '管理人员表';


#权限表
CREATE TABLE `carsharing`.`right` (
  `right_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL COMMENT '用户名',
  `employee_number` INT NULL COMMENT '工号',
  PRIMARY KEY (`right_id`))
COMMENT = '权限管理表';
