
ALTER TABLE "tb_profiles" ADD FOREIGN KEY ("id") REFERENCES "tb_users" ("id");

ALTER TABLE "tb_groups_subjects" ADD FOREIGN KEY ("id_group") REFERENCES "tb_groups" ("id");
ALTER TABLE "tb_groups_subjects" ADD FOREIGN KEY ("id_subject") REFERENCES "tb_subjects" ("id");

ALTER TABLE "tb_activities" ADD FOREIGN KEY ("subject_id") REFERENCES "tb_subjects" ("id");

ALTER TABLE "tb_groups" ADD FOREIGN KEY ("user_id") REFERENCES "tb_users" ("id");

ALTER TABLE "tb_activities" ADD FOREIGN KEY ("user_id") REFERENCES "tb_users" ("id");

ALTER TABLE "tb_activities" ADD FOREIGN KEY ("activity_type_id") REFERENCES "tb_type_activities" ("id");

ALTER TABLE "tb_subjects" ADD FOREIGN KEY ("user_id") REFERENCES "tb_users" ("id");
