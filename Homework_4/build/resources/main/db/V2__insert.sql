INSERT INTO teacher (name)
VALUES ('Volkov'),
       ('Trushin'),
       ('Ivanov');

INSERT INTO student (name)
VALUES ('Novikov'),
       ('Bazunov'),
       ('Petrov');

INSERT INTO training_course (name)
VALUES ('OOP'),
       ('Math'),
       ('History');

INSERT INTO marks
SELECT b.id, a.id, true, 5
FROM student as a, training_course as b
WHERE (a.name = 'Novikov' and b.name = 'OOP');

INSERT INTO course_for_student
SELECT b.id, a.id, true
FROM student as a, training_course as b
WHERE (a.name = 'Bazunov' and b.name = 'Math');

INSERT INTO timetable
SELECT b.id, a.id, '09.11.2022'
FROM teacher as a, training_course as b
WHERE (a.name = 'Volkov' and b.name = 'History');