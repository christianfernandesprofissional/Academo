ALTER TABLE
    tb_activities
ADD COLUMN notification_date DATE;

ALTER TABLE
    tb_activities
RENAME COLUMN "date" TO activity_date;