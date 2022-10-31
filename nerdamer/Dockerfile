FROM ubuntu:latest

COPY ./nerdamer /opt/nerdamer
WORKDIR /opt/nerdamer


RUN mkdir /usr/local/nvm
ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 14.18.1



RUN rm /bin/sh && ln -s /bin/bash /bin/sh
RUN apt update && apt upgrade -y
RUN apt install curl -y

RUN curl https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash \
    && . $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default \
    && cd /opt/nerdamer \
    && npm i \
    && npm install --save-dev @stryker-mutator/core



ENV NODE_PATH $NVM_DIR/v$NODE_VERSION/lib/node_modules
ENV PATH $NVM_DIR/versions/node/v$NODE_VERSION/bin:$PATH

CMD ["npx", "stryker", "run", "--ignoreStatic"]
