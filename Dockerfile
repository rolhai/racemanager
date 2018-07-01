# Copyright 2018 rolhai.
# Licensed under the Apache License, Version 2.0

FROM centos:7
LABEL maintainer="rolhai" description="Base image for racemanager images"

# install java
RUN yum update -y \
  && yum -y install unzip \
  && yum -y install java-1.8.0-openjdk-devel \
  && yum clean all
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0
ENV PATH "$PATH":/${JAVA_HOME}/bin:.:

# config wildfyl
ENV WILDFLY_USER wildflyadmin
ENV WILDFLY_PASS wildflyadmin

# install wildfly
ENV WILDFLY_VERSION 10.1.0.Final
ENV WILDFLY_INSTALL_DIR /opt
ENV WILDFLY_HOME ${WILDFLY_INSTALL_DIR}/wildfly-${WILDFLY_VERSION}
ENV DEPLOYMENT_DIR ${WILDFLY_HOME}/standalone/deployments/
ENV CONFIGURATION_DIR ${WILDFLY_HOME}/standalone/configuration
RUN useradd -b /opt -m -s /bin/sh -d ${WILDFLY_INSTALL_DIR} wildflyadmin && echo wildflyadmin:wildflyadmin | chpasswd
RUN curl -O https://download.jboss.org/wildfly/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.zip \
    && unzip wildfly-${WILDFLY_VERSION}.zip -d ${WILDFLY_INSTALL_DIR} \
    && rm wildfly-${WILDFLY_VERSION}.zip \
    && chown -R wildflyadmin:wildflyadmin /opt \
    && chmod a+x ${WILDFLY_HOME}/bin/standalone.sh \
    && chmod -R a+rw ${WILDFLY_INSTALL_DIR}
USER wildflyadmin
ENTRYPOINT ${WILDFLY_HOME}/bin/standalone.sh -b=0.0.0.0
EXPOSE 8080

# config database
# https://github.com/christianmetz/wildfly-mysql/blob/master/Dockerfile
ENV DB_NAME racemanager
ENV DB_USER rmdev
ENV DB_PASS rmdev

COPY ./services/target/racemanager-services.war ${DEPLOYMENT_DIR}
COPY ./webapp/target/racemanager-webapp.war ${DEPLOYMENT_DIR}
