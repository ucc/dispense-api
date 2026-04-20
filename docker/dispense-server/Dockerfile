FROM gcc:11.3

RUN apt-get update && apt-get install -y --no-install-recommends \
    libncurses-dev \
    libmodbus-dev \
    libsqlite3-dev \
    libident-dev \
    python2 \
    python2-dev \
    python-is-python3 \
    screen \
    sqlite3 \
    && rm -rf /var/lib/apt/lists/*

RUN git clone https://gitlab.ucc.asn.au/UCC/OpenDispense2.git /usr/src/OpenDispense2
RUN make -C /usr/src/OpenDispense2/src/

# Python 2 deps for virtualcoke
RUN curl -fsSL https://bootstrap.pypa.io/pip/2.7/get-pip.py | python2 \
    && python2 -m pip install incremental==21.3.0 \
    && python2 -m pip install --no-build-isolation twisted==20.3.0 cryptography bcrypt pyasn1 pymodbus==1.5.2 npyscreen

# Python 3 deps (if needed by other tools)
RUN curl -fsSL https://bootstrap.pypa.io/get-pip.py | python3 \
    && python3 -m pip install npyscreen twisted

RUN git clone https://gitlab.ucc.asn.au/UCC/virtualcoke.git /usr/src/virtualcoke

COPY dispsrv.conf /usr/src/OpenDispense2/dispsrv.conf
RUN mkdir -p /etc/opendispense
COPY client.conf /etc/opendispense/client.conf
COPY entrypoint.sh /usr/src/OpenDispense2/entrypoint.sh
RUN chmod +x /usr/src/OpenDispense2/entrypoint.sh

WORKDIR /usr/src/OpenDispense2
ENV TERM=xterm
EXPOSE 11021

ENTRYPOINT ["./entrypoint.sh"]
