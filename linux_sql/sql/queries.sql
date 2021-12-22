



SELECT cpu_number, id AS host_id, total_mem
FROM host_info
ORDER BY  cpu_number ,total_mem DESC;

--Function to return average memory usage

 CREATE FUNCTION get_avg_mem ( tollmem varchar ,  usedmemo varchar)
 returns varchar
     language plpgsql
 AS
 $$
declare  tl_mem numeric;
declare  used_mem numeric ;
declare avg_mem numeric ;
declare rslt varchar ;

BEGIN
-- Explicit converion and we store the value in variable
   SELECT   CAST(tollmem AS numeric) INTO tl_mem ;
   SELECT   CAST(usedmemo AS numeric ) INTO used_mem ;

    avg_mem := (used_mem/tl_mem)*100 ;

    SELECT CAST(avg_mem AS VARCHAR) INTO rslt ;

 return rslt ;
END
 $$;


-- Function that return timestamp for every 5 min
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

--  Percentage of average memory  by host 
SELECT 
  host_id, hostname , 
  round5(host_usage.timestamp)       AS timestamp_5min , 
  get_avg_mem(total_mem,memory_free) AS avg_mem
FROM host_usage , host_info 
ORDER BY host_id;

--  Detecting System failure
SELECT  host_id ,Count( host_id ) as datapoint, round5(host_usage.timestamp) as timestamp_5min
FROM host_usage 
GROUP BY host_id ,timestamp 
HAVING Count(host_id) < 3 ;


