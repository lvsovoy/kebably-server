let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
      scanner.addListener('scan', function (content) {
        callAddOrder(content);
      });
      Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
          scanner.start(cameras[0]);
        } else {
          console.error('No cameras found.');
        }
      }).catch(function (e) {
        console.error(e);
      });

      function callAddOrder(content) {
          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
               var rData = JSON.parse(this.responseText);
               console.log(rData.orderId);
               console.log(this.responseText);
               location.reload();
               }
          };
          xhttp.open("POST", "/orders", true);
          xhttp.setRequestHeader("Content-type", "application/json");
          xhttp.send(content);
      }

      function callMarkOrderAsDone(order) {
            var xhttp = new XMLHttpRequest();

            order.status = "DONE";

            xhttp.onreadystatechange = function() {
                 if (this.readyState == 4 && this.status == 200) {
                 var rData = JSON.parse(this.responseText);
                 console.log(rData.orderId);
                 console.log(this.responseText);
                 location.reload();
                 }
            };

            xhttp.open("PUT", "/orders/" + order.orderId, true);
            xhttp.setRequestHeader("Content-type", "application/json");
            console.log(JSON.stringify(order.status));
            xhttp.send("{\"status\": \"DONE\"}");
        }

    function callDeleteOrder(order) {
          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
               console.log("deleted order#"+order.orderId);
               console.log(this.responseText);
               location.reload();
               }
          };
          xhttp.open("DELETE", "/orders/" + order.orderId, true);
          xhttp.setRequestHeader("Content-type", "application/json");
          xhttp.send();
      }