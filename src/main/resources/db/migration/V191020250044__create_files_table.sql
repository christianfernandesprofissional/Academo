CREATE TABLE tb_files (
    "uuid" VARCHAR(255) PRIMARY KEY,
    "file_name" VARCHAR(255) NOT NULL,
    "path" TEXT NOT NULL,
    "file_type" VARCHAR(255) NOT NULL,
    "size" BIGINT,
    "created_at" timestamp,
    "updated_at" timestamp
);