#!/bin/bash

sudo docker compose down
sudo docker image rm img-secondhand-backend
sudo docker image build -t img-secondhand-backend .
sudo docker compose up -d