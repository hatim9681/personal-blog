import React from "react";
import {
  Navbar,
  MobileNav,
  Typography,
  Button,
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
  Avatar,
  Card,
  IconButton,
} from "@material-tailwind/react";
import {
  CubeTransparentIcon,
  UserCircleIcon,
  ChevronDownIcon,
  PowerIcon,
  RocketLaunchIcon,
  Bars2Icon,
} from "@heroicons/react/24/solid";
import { useStateContext } from "../../context/context";
import logo from "./logo.png";
import { Navigate } from "react-router-dom";

function ProfileMenu({ image }) {
  const [isMenuOpen, setIsMenuOpen] = React.useState(false);

  const closeMenu = () => setIsMenuOpen(false);

  const log = () => {
    console.log("dsc");
  };

  const profileMenuItems = [
    {
      label: "My Profile",
      icon: UserCircleIcon,
      onclick: log,
    },
    {
      label: "Sign Out",
      icon: PowerIcon,
      onclick: log,
    },
  ];

  const test = () => {
    localStorage.clear();
    window.location.reload();
  };

  return (
    <Menu open={isMenuOpen} handler={setIsMenuOpen} placement="bottom-end">
      <MenuHandler>
        <Button
          variant="text"
          color="blue-gray"
          className="flex items-center gap-1 rounded-full py-0.5 pr-2 pl-0.5 lg:ml-auto"
        >
          <Avatar
            variant="circular"
            size="sm"
            alt="tania andrew"
            className="border border-gray-900 p-0.5"
            src={image}
          />
          <ChevronDownIcon
            strokeWidth={2.5}
            className={`h-3 w-3 transition-transform ${
              isMenuOpen ? "rotate-180" : ""
            }`}
          />
        </Button>
      </MenuHandler>
      <MenuList className="p-1">
        <MenuItem
          onClick={closeMenu}
          className={`flex items-center gap-2 rounded`}
        >
          {React.createElement(UserCircleIcon, {
            className: `h-4 w-4`,
            strokeWidth: 2,
          })}
          <Typography
            as="span"
            variant="small"
            className="font-normal"
            color={"inherit"}
          >
            My Profile
          </Typography>
        </MenuItem>
        <MenuItem
          onClick={() => test()}
          className={`flex items-center gap-2 rounded hover:bg-red-500/10 focus:bg-red-500/10 active:bg-red-500/10`}
        >
          {React.createElement(PowerIcon, {
            className: `h-4 w-4  "text-red-500" `,
            strokeWidth: 2,
          })}
          <Typography
           
            as="span"
            variant="small"
            className="font-normal"
            color={"red"}
          >
            Sign Out
          </Typography>
        </MenuItem>
      </MenuList>
    </Menu>
  );
}

// nav list menu
const navListMenuItems = [
  {
    title: "@material-tailwind/html",
    description:
      "Learn how to use @material-tailwind/html, packed with rich components and widgets.",
  },
  {
    title: "@material-tailwind/react",
    description:
      "Learn how to use @material-tailwind/react, packed with rich components for React.",
  },
  {
    title: "Material Tailwind PRO",
    description:
      "A complete set of UI Elements for building faster websites in less time.",
  },
];

function NavListMenu() {
  const [isMenuOpen, setIsMenuOpen] = React.useState(false);

  const renderItems = navListMenuItems.map(({ title, description }) => (
    <a href="#" key={title}>
      <MenuItem>
        <Typography variant="h6" color="blue-gray" className="mb-1">
          {title}
        </Typography>
        <Typography variant="small" color="gray" className="font-normal">
          {description}
        </Typography>
      </MenuItem>
    </a>
  ));

  return (
    <React.Fragment>
      <Menu allowHover open={isMenuOpen} handler={setIsMenuOpen}>
        <MenuList className="hidden w-[36rem] grid-cols-7 gap-3 overflow-visible lg:grid">
          <Card
            color="blue"
            shadow={false}
            variant="gradient"
            className="col-span-3 grid h-full w-full place-items-center rounded-md"
          >
            <RocketLaunchIcon strokeWidth={1} className="h-28 w-28" />
          </Card>
          <ul className="col-span-4 flex w-full flex-col gap-1">
            {renderItems}
          </ul>
        </MenuList>
      </Menu>
    </React.Fragment>
  );
}

// nav list component
const navListItems = [
  {
    label: "Blogs",
    href: "/myblogs",

    icon: CubeTransparentIcon,
  },
  {
    label: "My Blogs",
    href: "/myblogs",
    icon: UserCircleIcon,
  },
];

function NavList() {
  return (
    <ul className="mt-2 mb-4 flex flex-col  gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center">
      <NavListMenu />
      {navListItems.map(({ label, icon, href }, key) => (
        <Typography
          key={label}
          as="a"
          href={href}
          variant="small"
          color="gray"
          className="font-medium text-blue-gray-500"
        >
          <MenuItem className="flex items-center gap-2 lg:rounded-full">
            {React.createElement(icon, { className: "h-[18px] w-[18px]" })}{" "}
            <span className="text-gray-900"> {label}</span>
          </MenuItem>
        </Typography>
      ))}
    </ul>
  );
}

export function ComplexNavbar() {
  const [isNavOpen, setIsNavOpen] = React.useState(false);
  const { currentUser } = useStateContext();

  const toggleIsNavOpen = () => setIsNavOpen((cur) => !cur);

  React.useEffect(() => {
    window.addEventListener(
      "resize",
      () => window.innerWidth >= 960 && setIsNavOpen(false)
    );
  }, []);

  return (
    <Navbar className="mx-auto max-w-screen-2xl p-2 mb-4 bg-white  shadow-gray-400 lg:rounded-full lg:pl-6">
      <div className="relative mx-auto flex items-center justify-between text-blue-gray-900">
        <Typography
          as="a"
          href="#"
          className="mr-4 ml-2 cursor-pointer py-1.5 font-medium"
        >
          <Avatar src={logo} alt="avatar" size="xl" />
        </Typography>
        <div className="hidden lg:block">
          <NavList />
        </div>
        <IconButton
          size="sm"
          color="blue-gray"
          variant="text"
          onClick={toggleIsNavOpen}
          className="ml-auto mr-2 lg:hidden"
        >
          <Bars2Icon className="h-6 w-6" />
        </IconButton>

        {currentUser ? (
          <ProfileMenu image={currentUser.image} />
        ) : (
          <>
            <Button size="sm" variant="text">
              <span>Sign in</span>
            </Button>
            <Button size="sm" variant="text">
              <span>Sign up</span>
            </Button>
          </>
        )}
      </div>
      <MobileNav open={isNavOpen} className="overflow-scroll">
        <NavList />
      </MobileNav>
    </Navbar>
  );
}
