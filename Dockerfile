FROM codercom/code-server

MAINTAINER public@shawnzhong.com

EXPOSE 63900
EXPOSE 8080 
EXPOSE 5000
EXPOSE 5001
EXPOSE 5002

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
	    make \ 
	    sqlite3 \ 
	    dnsutils \
	    wget \
	    python-pip && \
    apt-get clean && apt-get autoremove && rm -rf /var/lib/apt/lists/*

# install jdk
RUN wget https://download.java.net/openjdk/jdk8u40/ri/openjdk-8u40-b25-linux-x64-10_feb_2015.tar.gz && \
	tar xvzf openjdk-8u40-b25-linux-x64-10_feb_2015.tar.gz && \
	rm openjdk-8u40-b25-linux-x64-10_feb_2015.tar.gz && \
	rm java-se-8u40-ri/src.zip && \
	mkdir -p /usr/lib/jvm/ && \
	mv java-se-8u40-ri /usr/lib/jvm/

ENV PATH="/usr/lib/jvm/java-se-8u40-ri/bin:/usr/lib/jvm/java-se-8u40-ri/jre/bin:${PATH}" 


# install flask for 3.9.3: JSONP
RUN pip install flask

ENTRYPOINT ["code-server", "--allow-http", "--no-auth", "--port=63900", "EXERCISES"]