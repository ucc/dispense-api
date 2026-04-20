#!/bin/sh
set -e

# Start virtualcoke in a detached screen session
screen -dmS virtualcoke python2 ../virtualcoke/virtualcoke.py

# Start dispsrv in the background so it can initialise the SQLite schema
./dispsrv &
DISPSRV_PID=$!

# Wait for the database to be created
until [ -f "cokebank.db" ]; do sleep 0.2; done
# Give it a moment to finish writing the schema
sleep 1

# add test users
sqlite3 cokebank.db <<'SQL'
INSERT OR IGNORE INTO accounts (acct_name, acct_uid, acct_balance, acct_is_coke, acct_is_admin, acct_is_door) VALUES
    ('alice',   1001, 500, 1, 0, 0),
    ('bob',     1002, 500, 1, 0, 1),
    ('charlie', 1003, 500, 1, 1, 0),
    ('dave',    1004, 500, 1, 0, 0),
    ('eve',     1005, 500, 1, 1, 1);
SQL

# Forward SIGTERM/SIGINT to dispsrv so the container shuts down cleanly
trap "kill $DISPSRV_PID" TERM INT
wait $DISPSRV_PID
