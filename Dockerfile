FROM codercom/code-server

MAINTAINER public@shawnzhong.com

EXPOSE 63900
EXPOSE 8080 

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk python2.7 make sqlite3 dnsutils && \
    apt-get clean

 RUN ln -s /usr/bin/python2.7 /usr/bin/python

ENTRYPOINT ["code-server", "--allow-http", "--no-auth", "--port=63900", "EXERCISES"]