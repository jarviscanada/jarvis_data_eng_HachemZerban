
CREATE TABLE PUBLIC.host_info
  (
     id                SERIAL NOT NULL,
     hostname          VARCHAR NOT NULL,
     cpu_number        VARCHAR NOT NULL,
     cpu_architecture  VARCHAR NOT NULL,
     cpu_model         VARCHAR NOT NULL,
     cpu_mhz           VARCHAR NOT NULL,
     L2_cache          VARCHAR NOT NULL,
     total_mem         VARCHAR NOT NULL,
     "timestamp"       TIMESTAMP NOT NULL,

     PRIMARY KEY (id),
     UNIQUE(hostname) 
 
  );

INSERT INTO host_info (id,hostname,cpu_number,cpu_architecture,
cpu_model,cpu_mhz,L2_cache,total_mem,timestamp) VALUES 

(1,'spry-framework-236416.internal','1','x86_64','Intel(R) Xeon(R) CPU @ 2.30GHz','2300.000','256','601324','2019-05-29 17:49:53');


CREATE TABLE PUBLIC.host_usage 
  (
       "timestamp"         TIMESTAMP NOT NULL,
       host_id             SERIAL NOT NULL,
       memory_free         VARCHAR NOT NULL,
       cpu_idle            VARCHAR NOT NULL,
       cpu_kernel          VARCHAR NOT NULL,
       disk_io             VARCHAR NOT NULL,
       disk_available      VARCHAR NOT NULL,

      CONSTRAINT fk_host 
          FOREIGN KEY (host_id)
            REFERENCES host_info(id) 

     );
       
INSERT INTO host_usage("timestamp",host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available) VALUES

('2019-05-29 16:53:28',1,'256','95','0','0','31220');
