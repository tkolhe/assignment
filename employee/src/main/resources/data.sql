INSERT INTO department (dept_code, dept_name) VALUES ('D101', 'DEV');
INSERT INTO department (dept_code, dept_name) VALUES ('D102', 'Test');
INSERT INTO department (dept_code, dept_name) VALUES ('D103', 'DevOps');

INSERT INTO project (project_code, name, dept_code) VALUES ('P101', 'P_Test', 'D101');
INSERT INTO project (project_code, name, dept_code) VALUES ('P102', 'banking', 'D101');
INSERT INTO project (project_code, name, dept_code) VALUES ('P103', 'finance', 'D102');
INSERT INTO project (project_code, name, dept_code) VALUES ('P104', 'customer', 'D103');

INSERT INTO employee (dept_code, emp_code, project_code, first_name, last_name) VALUES ('D101', 'E101', 'P101', 'Tanuja', 'Kolhe');
INSERT INTO employee (dept_code, emp_code, project_code, first_name, last_name) VALUES ('D101', 'E101', 'P102', 'Tanuja', 'kolhe');
INSERT INTO employee (dept_code, emp_code, project_code, first_name, last_name) VALUES ('D102', 'E102', 'P103', 'Mitisha', 'Patil');
INSERT INTO employee (dept_code, emp_code, project_code, first_name, last_name) VALUES ('D103', 'E102', 'P104', 'Mitisha', 'Patil');
INSERT INTO employee (dept_code, emp_code, project_code, first_name, last_name) VALUES ('D102', 'E103', 'P103', 'Rahul', 'T');
