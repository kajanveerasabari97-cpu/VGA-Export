# Vercel Speed Insights Setup

This project now includes Vercel Speed Insights to track web performance metrics.

## What was installed

- **Package**: `@vercel/speed-insights` (v1.3.1)
- **Implementation**: Custom vanilla JavaScript integration for static HTML

## Files Modified/Created

1. **package.json** - Added `@vercel/speed-insights` dependency
2. **package-lock.json** - Locked dependencies
3. **public/speed-insights.js** - Custom Speed Insights initialization script
4. **public/index.html** - Added Speed Insights script tag in the `<head>` section
5. **vercel.json** - Configuration file to enable Speed Insights on Vercel

## How it works

The implementation uses a custom JavaScript file (`speed-insights.js`) that:
1. Initializes the Speed Insights queue (`window.si`)
2. Detects the environment (Vercel production vs. development/local)
3. Injects the appropriate Speed Insights tracking script
4. Handles errors gracefully if the script fails to load

When deployed to Vercel, the script automatically loads from `/_vercel/speed-insights/script.js`.
In other environments, it falls back to the Vercel CDN.

## Configuration

The `vercel.json` file enables Speed Insights:

```json
{
  "speedInsights": {
    "enable": true
  }
}
```

## Usage

No additional configuration is needed. Speed Insights will automatically:
- Track Core Web Vitals (LCP, FID, CLS, FCP, TTFB, INP)
- Send metrics to your Vercel dashboard
- Only track in production (not in development mode)

## Viewing Metrics

After deploying to Vercel:
1. Go to your Vercel dashboard
2. Select your project
3. Navigate to the "Speed Insights" tab
4. View real-time performance metrics from actual users

## References

- [Vercel Speed Insights Documentation](https://vercel.com/docs/speed-insights)
- [Quickstart Guide](https://vercel.com/docs/speed-insights/quickstart)
- [Package Documentation](https://vercel.com/docs/speed-insights/package)
