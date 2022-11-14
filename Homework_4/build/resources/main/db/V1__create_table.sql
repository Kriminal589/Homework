CREATE TABLE teacher (
    id      SERIAL  NOT NULL,
    name    VARCHAR NOT NULL,
    CONSTRAINT teacher_pk PRIMARY KEY (id)
);

CREATE TABLE student (
    id      SERIAL  NOT NULL,
    name    VARCHAR NOT NULL,
    CONSTRAINT student_id PRIMARY KEY (id)
);

CREATE TABLE training_course (
    id          SERIAL  NOT NULL,
    name        VARCHAR NOT NULL,
    count_hours INT     DEFAULT NULL,
    CONSTRAINT training_course_pk PRIMARY KEY (id)
);

CREATE TABLE marks (
    training_course_id  INT     NOT NULL REFERENCES training_course (id) ON UPDATE CASCADE ON DELETE CASCADE,
    student_id          INT     NOT NULL REFERENCES student (id) ON UPDATE CASCADE ON DELETE CASCADE,
    visit               BOOLEAN NOT NULL,
    mark_homework       INT     NOT NULL,
    CONSTRAINT marks_pk PRIMARY KEY (training_course_id, student_id)
);

CREATE TABLE course_for_student (
    training_course_id  INT     NOT NULL REFERENCES training_course (id) ON UPDATE CASCADE ON DELETE CASCADE,
    student_id          INT     NOT NULL REFERENCES student (id) ON UPDATE CASCADE ON DELETE CASCADE,
    enrolled            BOOLEAN NOT NULL,
    CONSTRAINT course_for_student_pk PRIMARY KEY (training_course_id, student_id)
);

CREATE TABLE timetable (
    training_course_id  INT     NOT NULL REFERENCES training_course (id) ON UPDATE CASCADE ON DELETE CASCADE,
    teacher_id          INT     NOT NULL REFERENCES teacher (id) ON UPDATE CASCADE ON DELETE CASCADE,
    date                DATE    NOT NULL,
    CONSTRAINT timetable_pk PRIMARY KEY (training_course_id, teacher_id)
);