INSERT INTO `student` (`id`,`name`) VALUES (1,'Abhishek Malvadkar');
INSERT INTO `student` (`id`,`name`) VALUES (2,'Rushikesj Malvadkar');
INSERT INTO `student` (`id`,`name`) VALUES (3,'Sunita Malvadkar');

INSERT INTO `course` (`id`,`name`,`price`) VALUES (1,'Spring Boot By Abhishek Sir',500.00);
INSERT INTO `course` (`id`,`name`,`price`) VALUES (2,'Java by Rushikesh Sir',600.00);

INSERT INTO `student_course` (`id`,`enrolled_date_time`,`course_id`,`student_id`) VALUES (1,'2023-07-08 12:05:24.000000',1,1);
INSERT INTO `student_course` (`id`,`enrolled_date_time`,`course_id`,`student_id`) VALUES (2,'2023-07-08 12:06:26.000000',1,2);
INSERT INTO `student_course` (`id`,`enrolled_date_time`,`course_id`,`student_id`) VALUES (3,'2023-07-08 12:06:52.000000',1,3);