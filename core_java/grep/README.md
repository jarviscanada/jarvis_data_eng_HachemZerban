
# Introduction
 The grep application is a  Java app that mirrors the Linux `grep` command functionality that enables users to search matching strings from files in any operating system and then store the search results in an output file.
The application can be used by any developer to find files in their operating systems.
The app was built using Java 8 Stream API, IntelliJ IDEA, and it follows the Maven Standard layout directory.
We used Docker to deploy the app.


# Quick Start

The java grep takes 3 arguments :
`{regex_pattern} ${src_dir} ${outfile}`
- `regesx_pattern` = the text to match 
- `src_dir`= the source directory from where to start the search
- `outfile`= the output file that contains search results.
Launch Terminal and execute the following instruction :

set the environment variables : 
`docker_user=your_docker_id`
`regex_pattern=the regular expression you look for `
`src_dir=the source directory`
`outfile= the file in which you want to store search result`
download the image 
`docker pull techapps101/grep`
run the image on container :
`docker run --rm \
-v ``pwd``/data:/data -v ``pwd``/out:/out techapps101/grep \
${regex_pattern} ${src_dir} /out/${outfile}`

#Implementation
## Pseudocode
The app has two classes to search for a pattern in a directory: the `javagrepImp` is using the traditional for-loop implementation and the  `javagrepLambdaimp` is using Java 8 streams.

`javagrepImp` pseudocode :

`matchedLines = []
for file in listFiles(rootDir)
for line in readLines(file)
if containsPattern(line)
matchedLines.add(line)
writeToFile(matchedLines)`

`javagrepLambdaImp` pseudocode :

`matchedLines = []
for file in lsFileStream(getRootPath())
for line in rdlStream(file)
if containsPattern(line)
matchedLines.add(line)
writeToFileStr(matchedLines)`



## Performance Issue

One of the performance issues faced using the app was a  memory issue. When trying to read lines from heavy files, the app throws an `OutOfMemoryError` exception because the JVM is unable to allocate an object due to insufficient space in the Java heap.
To encounter this problem we modified the `rdlStream(line)` and added the `InputStream` and `BufferedReader` objects. This will help to avoid loading all the file contents into the memory. 


# Test

The app was tested manually. We created subdirectories that each of them contains sample data and test if the app will succeed in finding text patterns.
To test heavy files, we used the `java -Xms5m -Xmx5m` command to resize the allocated JVM memory to be less than the host memory capacity. 
We used IDE debugger to fix some Java 8 stream issues related to listing recursively all files in a directory. 

# Deployment
The java grep app has a docker file that contains all the commands a user could call on the command line to assemble an image.
to distribute the image we use  : `docker push ${docker_user}/grep`


# Improvement

- Adding the reoccurrence number of each line in the output.
- Adding the line number with the name of the file of each matched text
- Reformatting the output file.
