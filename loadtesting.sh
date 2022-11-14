locust --config config.conf
mkdir -p reports
mv ./loadtest*.csv ./reports/
read -p "Press enter to continue"
