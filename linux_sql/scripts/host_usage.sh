#!/bin/bash 




psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then  
    echo ' Command missing ' 
     exit 1 
    fi
#retrieve host data and store it in variables
hostname=$(hostname -f | xargs)
# retrieving the 3rd line row 18 and 19 
timestamp=$(vmstat -t | awk 'FNR == 3 {print $18" "$19}'| xargs)
vmstat_mb=$(vmstat --unit M)
#usage
memory_free=$(echo "$vmstat_mb"| awk 'FNR == 3 {print $4}' | xargs)
cpu_idle=$(echo "$vmstat_mb"| awk 'FNR == 3 {print $15}' | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk 'FNR == 3 {print $14}' | xargs)
disk_io=$(vmstat -d | awk 'FNR == 3 {print $10}' | xargs)

disk_available=$(df -BM | awk 'FNR == 6 {print $4}' | xargs)
#query to get the host id by host name for host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')"

#storing all retrieved data into the database
insert_stmt="INSERT INTO host_usage(timestamp,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available) 
VALUES ('$timestamp',$host_id,'$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available');"



#set up env var for pql cmd
export PGPASSWORD=$psql_password 
# executing the insert statement 
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?




