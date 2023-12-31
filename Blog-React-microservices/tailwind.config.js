/** @type {import('tailwindcss').Config} */

const withMT = require("@material-tailwind/react/utils/withMT");
const { nextui } = require("@nextui-org/react");

export default withMT({
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@material-tailwind/react/components/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@material-tailwind/react/theme/components/**/*.{js,ts,jsx,tsx}",
  
  ],
  theme: {
    screens: {
      sm: "540px",
      md: "720px",
      lg: "960px",
      "lg-max": { max: "960px" },
      xl: "1140px",
      "2xl": "1320px",
    },
    extend: {},
  },
  darkMode: "class",
  plugins: [nextui()],
})

