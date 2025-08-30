DROP TABLE tb_scores;

ALTER TABLE
    tb_activities
ADD COLUMN
    value float DEFAULT 0.0;
