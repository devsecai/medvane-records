provider "aws" { region = "eu-west-2" }

resource "aws_s3_bucket" "phi" { bucket = "medvane-phi-prod" }
# MED-IAC-001: PHI bucket public-read.
resource "aws_s3_bucket_acl" "phi" {
  bucket = aws_s3_bucket.phi.id
  acl    = "public-read"
}

resource "aws_db_instance" "medvane" {
  identifier        = "medvane-prod"
  engine            = "mysql"
  engine_version    = "8.0"
  instance_class    = "db.t3.medium"
  allocated_storage = 50
  username          = "medvane"
  password          = "ChangeMe2024!"

  # MED-IAC-002 / MED-IAC-003
  publicly_accessible = true
  storage_encrypted   = false
  skip_final_snapshot = true
}
