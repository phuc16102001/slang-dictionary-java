javac -cp src; src/hcmus/fit/vuongphuc/main/Main.java -d bin
if not exist jar mkdir jar
cd bin
jar -cvfm ../jar/output.jar ../META-INF/MANIFEST.mf hcmus/fit/vuongphuc/constant/*.class hcmus/fit/vuongphuc/main/*.class hcmus/fit/vuongphuc/model/*.class hcmus/fit/vuongphuc/ui/*.class