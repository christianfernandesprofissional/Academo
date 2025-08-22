CREATE TABLE "tb_users" (
                         "id" serial PRIMARY KEY,
                         "username" varchar UNIQUE,
                         "password" varchar,
                         "email" varchar UNIQUE,
                         "created_at" timestamp,
                         "updated_at" timestamp
);

CREATE TABLE "tb_type_activities" (
                                  "id" serial PRIMARY KEY,
                                  "name" varchar,
                                  "description" text,
                                  "user_id" integer,
                                  "created_at" timestamp,
                                  "updated_at" timestamp
);

CREATE TABLE "tb_profiles" (
                            "id" serial PRIMARY KEY,
                            "name" varchar,
                            "created_at" timestamp,
                            "updated_at" timestamp
);

CREATE TABLE "tb_groups" (
                          "id" serial PRIMARY KEY,
                          "name" varchar,
                          "description" varchar,
                          "user_id" integer,
                          "is_active" bool,
                          "created_at" timestamp,
                          "updated_at" timestamp
);

CREATE TABLE "tb_subjects" (
                            "id" serial PRIMARY KEY,
                            "name" varchar,
                            "description" varchar,
                            "user_id" integer,
                            "is_active" bool,
                            "created_at" timestamp,
                            "updated_at" timestamp
);

CREATE TABLE "tb_groups_subjects" (
                                  "id_group" integer,
                                  "id_subject" integer,
                                  PRIMARY KEY ("id_group", "id_subject")
);

CREATE TABLE "tb_activities" (
                              "id" serial PRIMARY KEY,
                              "date" date,
                              "name" varchar,
                              "description" varchar,
                              "user_id" integer,
                              "SubjectId" integer,
                              "ActivityTypeId" integer,
                              "created_at" timestamp,
                              "updated_at" timestamp
);

CREATE TABLE "tb_scores" (
                          "id" serial PRIMARY KEY,
                          "value" float,
                          "user_id" integer,
                          "activity_id" integer,
                          "created_at" timestamp,
                          "updated_at" timestamp
);

