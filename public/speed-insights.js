// Vercel Speed Insights initialization
// This script injects the Speed Insights tracking code
(function() {
  'use strict';
  
  // Initialize the queue for Speed Insights
  if (!window.si) {
    window.si = function() {
      (window.siq = window.siq || []).push(arguments);
    };
  }
  
  // Determine the appropriate script source based on environment
  function getScriptSrc() {
    // In production on Vercel, use the Vercel-hosted script
    if (window.location.hostname.includes('vercel.app') || 
        window.location.hostname.includes('vercel-speed-insights.com')) {
      return '/_vercel/speed-insights/script.js';
    }
    // For development or self-hosted, use the Vercel CDN
    return 'https://va.vercel-scripts.com/v1/speed-insights/script.js';
  }
  
  // Inject the Speed Insights script
  var script = document.createElement('script');
  script.src = getScriptSrc();
  script.defer = true;
  script.setAttribute('data-sdkn', '@vercel/speed-insights');
  script.setAttribute('data-sdkv', '1.3.1');
  
  script.onerror = function() {
    console.warn('[Vercel Speed Insights] Failed to load script. Please check if any content blockers are enabled.');
  };
  
  // Append to head
  if (document.head) {
    document.head.appendChild(script);
  } else {
    // If head is not ready yet, wait for DOM to be ready
    document.addEventListener('DOMContentLoaded', function() {
      document.head.appendChild(script);
    });
  }
})();
