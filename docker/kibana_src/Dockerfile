ARG NODE_VERSION=
FROM node:${NODE_VERSION}

RUN mkdir -p /usr/share/kibana/config
WORKDIR /usr/share/kibana
RUN groupadd --gid 1001 kibana \
  && useradd --uid 1001 --gid 1001 --groups 0 --home-dir /usr/share/kibana --no-create-home kibana
RUN chown -R kibana:0 /usr/share/kibana
USER kibana

COPY kibana /usr/share/kibana
COPY kibana.yml /usr/share/kibana/config/kibana.yml

RUN git config --global user.email "none@example.com"
RUN git config --global user.name "None"
RUN git init && git add . && git commit -a -m "init commit"
ENV HOME=/usr/share/kibana
ENV NODE_OPTIONS= --max-old-space-size=4096
ENV FORCE_COLOR=1
ENV BABEL_DISABLE_CACHE=true

RUN yarn kbn clean
RUN yarn kbn bootstrap

EXPOSE 5601
ENTRYPOINT ["node", "./scripts/kibana", "--no-base-path", "--dev", "--no-dev-config"]
CMD ["--config", "/usr/share/kibana/config/kibana.yml"]


HEALTHCHECK --interval=10s --timeout=5s --start-period=1m --retries=5 CMD curl -sSL http://127.0.0.1:5601/login|grep -v 'Kibana server is not ready yet'
