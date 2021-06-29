# bp0628
ToolRental is a mock tool rental service with a checkout screen and rental agreement screen.

## Deploy
The web.xml is configured for running on a Wildfly server. Below are the steps to run and deploy on a standalone server.

1. Build .war file from ToolRental project
2. Place file in standalone deployments directory
3. Launch Wildfly server with standalone.sh or standalone.bat depending on OS
4. Open browser and navigate to localhost:8080/ToolRental

## Run
The initial screen is the checkout. Below is the walkthrough for generating a rental agreement for a customer.

1. Click the type of tool desired for rental
2. Type the number of days the tool will be checked out for
3. Apply a discount percentage 0 - 100
4. Click checkout
