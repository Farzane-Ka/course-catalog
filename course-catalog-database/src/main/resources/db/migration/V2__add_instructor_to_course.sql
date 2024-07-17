CREATE TABLE instructor
(
    instructor_id UUID NOT NULL,
    name      VARCHAR,
    PRIMARY KEY (instructor_id)
);

ALTER TABLE course
ADD COLUMN IF NOT EXISTS instructor_id UUID NOT NULL;

ALTER TABLE course
ADD CONSTRAINT fk_course
FOREIGN KEY (instructor_id)
REFERENCES instructor (instructor_id);