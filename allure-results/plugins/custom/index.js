document.addEventListener("DOMContentLoaded", function() {
  // Add custom header
  const header = document.createElement("header");
  header.className = "custom-header";
  header.innerHTML = "Apple Regression Report | Executed by Nagaraj | Environment: QA | Browser: Chrome";
  document.body.insertBefore(header, document.body.firstChild);

  // Add custom footer
  const footer = document.createElement("footer");
  footer.className = "custom-footer";
  footer.innerHTML = " 2025 Nagaraj QA Automation | Powered by Allure";
  document.body.appendChild(footer);
});
