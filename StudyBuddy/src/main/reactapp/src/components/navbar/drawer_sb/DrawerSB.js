import {Drawer, List, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import React, {useState} from 'react';
import MenuIcon from "@mui/icons-material/Menu";
import IconButton from "@mui/material/IconButton";

function DrawerSB() {
    const [openDrawer, setOpenDrawer] = useState(false);
    const PAGES = ["Home", "Articles", "Create Article", "Account","Login", "Sign up"];
    const PAGESROUT = ["/", "/user/articles", "/user/article/create", "/user/account","/login", "/register"];
    return (
        <React.Fragment>
            <Drawer open={openDrawer} onClose={() => setOpenDrawer(false)}>
                <List>
                    {PAGES.map((page, index) =>(
                        <a className="nav-link" href={PAGESROUT[index]}>
                            <ListItemButton key={index}>
                                <ListItemIcon>
                                    <ListItemText> {page} </ListItemText>
                                </ListItemIcon>
                            </ListItemButton>
                        </a>
                    ))}

                </List>
            </Drawer>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                onClick={()=> setOpenDrawer(!openDrawer)}
                sx={{ marginLeft: "auto"}}
            >
                <MenuIcon />
            </IconButton>
        </React.Fragment>
    )
}
export default DrawerSB;